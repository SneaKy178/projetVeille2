<template>
  <div v-if="state.isLoggedIn">
    <form method="post" action="#" id="#" @submit.prevent="handleSubmit">
      <div class="form-group files file center box">
        <label for="file-upload" id="file" class="custom-file-upload cv">
          <i class="fa fa-upload" aria-hidden="true"></i>
          Upload
        </label>
        <input
          id="file-upload"
          type="file"
          ref="myFiles"
          v-on:change="getFile"
          multiple
        />
      </div>
      <button class="submit center">Submit</button>
    </form>
    <table>
      <tr>
        <th>Nom du cv</th>
        <th>Date de soumission</th>
        <th>Statut</th>
      </tr>
      <tr v-for="cv in cvs" v-bind:key="cv">
        <td>{{ cv.nom }}</td>
        <td>{{ cv.dateSoumission }}</td>
        <td>{{ cv.status }}</td>
      </tr>
    </table>
  </div>
  <div v-else class="center">
    <h1>Veuillez vous conneter</h1>
    <button @click="login">Login</button><br />
  </div>
</template>

<script>
window.$ = window.jQuery = require("jquery");

import Swal from "sweetalert2";
import { ref } from "vue";
import global from "./global";
export default {
  created() {
    this.fetchUser();
  },
  setup() {
    const { state } = global;
    const fullUser = ref({});
    const cvs = ref({});
    return { state, fullUser, cvs };
  },
  data() {
    return {
      files: [],
    };
  },
  methods: {
    fetchUser() {
      fetch(`http://localhost:9191/user/${this.state.courriel}`)
        .then((res) => {
          return res.json();
        })
        .then(async (data) => {
          this.fullUser = data;
          await this.fetchCvs();
        });
    },
    fetchCvs() {
      fetch(`http://localhost:9191/stage/cv/etudiant/${this.fullUser.id}`)
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          this.cvs = data;
        });
    },
    getFile() {
      this.files = this.$refs.myFiles.files[0];
      document.querySelector("#file").textContent = this.files.name;
    },
    fileToBase64(file, erreur) {
      if (file.length == 0) {
        Swal.fire({
          title: "Error!",
          text: "Veuillez choisir un fichier avant de soumettre.",
          icon: "error",
          confirmButtonText: "ok",
        });
      }
      if (file.value != 0) {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function () {
          erreur(null, reader.result);
        };
        reader.onerror = function (error) {
          erreur(error, null);
        };
      }
    },
    handleSubmit() {
      this.fileToBase64(this.files, (err, result) => {
        if (result) {
          result = result.substring(28);

          const studentCv = JSON.stringify({
            data: result,
            etudiant: this.fullUser,
            nom: this.files.name,
          });

          fetch("http://localhost:9191/stage/cv", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: studentCv,
          }).then(async (res) => {
            await this.fetchCvs();
          });
        }
      });
    },
    login() {
      this.$router.push("/login");
    },
  },
};
</script>
<style scoped>
table {
  margin-left: auto;
  margin-right: auto;
}
table,
td,
th {
  border: 1px solid black;
  text-align: center;
}

table {
  margin-top: 60px;
  width: 50%;
  border-collapse: collapse;
  background-color: lightgray;
}

table th {
  background-color: black;
  color: white;
}
input[type="file"] {
  margin-top: 150px;
  margin-left: 90px;
  color: white;
}

.custom-file-upload {
  border: 1px solid #ccc;
  border-style: dashed;
  padding: 6px 12px;
  cursor: pointer;

  width: 100px;
  height: 50px;
  position: absolute;
  top: 42%;
  left: 30%;
  color: white;
  width: 200px;
}

.center {
  margin-top: 50px;
  text-align: center;
  width: 400px;
  left: 50%;
  position: relative;
  transform: translate(-50%);
}

button {
  margin-top: 20px;
}

.box {
  width: 500px;
  height: 250px;
  padding-top: 20px;
  padding-bottom: 20px;

  border: 1px solid black;
  border-style: dashed;
  background-color: green;
}

button {
  background: #0b6dff;
  border: 0;
  padding: 10px 20px;
  margin-top: 20px;
  color: white;
  border-radius: 20px;
  font-size: 16px;
}

.cv {
  padding-top: 15px;
}

input[type="file"] {
  display: none;
}
</style>


