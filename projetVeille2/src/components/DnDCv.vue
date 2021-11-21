<template>
  <div v-if="state.isLoggedIn">
    <form method="post" action="#" id="#" @submit.prevent="handleSubmit">
      <div class="form-group files">
        <input
          type="file"
          id="file"
          ref="myFiles"
          class="custom-file-input"
          v-on:change="getFile"
          multiple
        />
      </div>
      <button class="submit">Submit</button>
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
  width: 50%;
  border-collapse: collapse;
  background-color: lightgray;
}

table th {
  background-color: black;
  color: white;
}
.center {
  margin-top: 50px;
  text-align: center;
}
</style>


