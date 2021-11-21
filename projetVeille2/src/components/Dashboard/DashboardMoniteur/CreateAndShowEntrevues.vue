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
  <h1>Liste des entrevues</h1>
  <table>
    <tr>
      <th>Titre de l'entrevue</th>
      <th>Date de l'entrevue</th>
      <th>Heure de l'entrevue</th>
      <th>Nom de l'étudiant</th>
    </tr>
    <tr v-for="entrevue in entrevues" v-bind:key="entrevue">
      <td>{{ entrevue.titre }}</td>
      <td>{{ entrevue.date }}</td>
      <td>{{ entrevue.time }}</td>
      <td>{{ entrevue.etudiant.prenom }} {{ entrevue.etudiant.nom }}</td>
    </tr>
  </table>
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
    const entrevues = ref({});
    return { state, fullUser, etudiants, entrevues };
  },
  methods: {
    fetchUser() {
      fetch(`http://localhost:9191/user/${this.state.courriel}`)
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          this.fullUser = data;
          this.fetchEntrevues();
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
    fetchEntrevues() {
      fetch(`http://localhost:9191/entrevue/moniteur/${this.fullUser.id}`)
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          this.entrevues = data;
          console.log(data, "entrevues data");
          console.log(this.entrevues, "entrevues");
        });
    },
    handleSubmit() {
      fetch(`http://localhost:9191/stage/etudiant/${this.etudiant}`)
        .then((response) => response.json())
        .then((data) => {
          const entrevue = JSON.stringify({
            titre: this.titre,
            date: this.date,
            time: this.time,
            etudiant: data,
            moniteur: this.fullUser,
            nomEntreprise: this.fullUser.nomEntreprise,
          });

          fetch("http://localhost:9191/entrevue", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: entrevue,
          }).then(async (res) => {
            await this.fetchEntrevues();
          });
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