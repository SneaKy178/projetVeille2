<template>
  <h1>Créer une entrevue</h1>
  <form @submit.prevent="handleSubmit">
    <table>
      <tr>
        <th>Titre</th>
        <th>Date</th>
        <th>Time</th>
        <th>Id de l'étudiant</th>
        <th>Créer</th>
      </tr>
      <tr>
        <td>
          <input type="text" required v-model="titre" placeholder="titre" />
        </td>
        <td>
          <input type="date" required v-model="date" placeholder="date" />
        </td>
        <td>
          <input type="time" required v-model="time" placeholder="time" />
        </td>
        <td>
          <select name="etudiant" required v-model="etudiant">
            <option>Please select</option>
            <option
              v-for="student in etudiants"
              v-bind:key="student"
              :value="student.id"
            >
              {{ student.prenom }} {{ student.nom }}
            </option>
          </select>
        </td>
        <td>
          <button class="form-field" type="submit">Créer</button>
        </td>
      </tr>
    </table>
  </form>
</template>
<script>
import { ref } from "vue";
import global from "../../global";
export default {
  data() {
    return {
      titre: "",
      date: "",
      time: "",
      etudiant: "",
    };
  },
  created() {
    this.fetchUser();
    this.fetchEtudiants();
  },
  setup() {
    const { state } = global;
    const fullUser = ref({});
    const etudiants = ref({});
    const objectEtudiant = ref({});
    return { state, fullUser, etudiants, objectEtudiant };
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
    fetchEtudiants() {
      fetch(`http://localhost:9191/stage/etudiants`)
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          this.etudiants = data;
        });
    },
    handleSubmit() {
      fetch(`http://localhost:9191/stage/etudiant/${this.etudiant}`)
        .then((response) => response.json())
        .then((data) => {
          var request = new XMLHttpRequest();
          request.open("POST", "http://localhost:9191/entrevue");
          request.setRequestHeader(
            "Content-Type",
            "application/json; charset=UTF-8"
          );

          const entrevue = JSON.stringify({
            titre: this.titre,
            date: this.date,
            time: this.time,
            etudiant: data,
            moniteur: this.fullUser,
            nomEntreprise: this.fullUser.nomEntreprise,
          });

          console.log(entrevue);
          request.send(entrevue);
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

h1 {
  margin-top: 20px;
  margin-bottom: 20px;
}
</style>