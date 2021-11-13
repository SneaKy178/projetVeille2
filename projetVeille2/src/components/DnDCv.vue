<template>
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
    <tr v-for="cv in cvs" v-bind:key="cv">
      <td>{{ cv.nom }}</td>
      <td>{{ cv.dateSoumission }}</td>
    </tr>
  </table>
</template>

<script>
import Swal from "sweetalert2";
import { ref } from "vue";
import global from "./global";
export default {
  created() {
    this.fetchUser();
    this.fetchCvs();
    setInterval(() => this.fetchCvs(), 100);
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
        .then((data) => {
          this.fullUser = data;
        });
    },
    fetchCvs() {
      fetch(`http://localhost:9191/stage/getCVs`)
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
    fileToBase64(file, cb) {
      if (file == null) {
        Swal.fire({
          title: "Error!",
          text: "Vous devez sélectionner un fichier avant de submit",
          icon: "error",
          confirmButtonText: "ok",
        });
      } else if (file != null) {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function () {
          cb(null, reader.result);
        };
        reader.onerror = function (error) {
          cb(error, null);
        };
      }
    },
    handleSubmit() {
      this.fileToBase64(this.files, (err, result) => {
        if (result) {
          result = result.substring(28);

          var request = new XMLHttpRequest();
          request.open("POST", "http://localhost:9191/stage/cv");
          request.setRequestHeader(
            "Content-Type",
            "application/json; charset=UTF-8"
          );
          const studentCv = JSON.stringify({
            data: result,
            etudiant: this.fullUser,
            nom: this.files.name,
          });
          request.send(studentCv);
        }
      });
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
}

table {
  width: 50%;
  border-collapse: collapse;
}
</style>


