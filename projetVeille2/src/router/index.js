import { createRouter, createWebHistory } from 'vue-router'
import SignupForm from '../components/SignupForm.vue'
import FormEtudiant from '../components/Forms/FormEtudiant.vue'
import FormMoniteur from '../components/Forms/FormMoniteur.vue'
import FormSuperviseur from '../components/Forms/FormSuperviseur.vue'
import Login from '../components/Login.vue'
import AccountDetails from '../components/AccountDetails.vue'
import Home from '../components/Home.vue'

const routes = [
  {
    path: '/',
    component: Login
  },
  {
    path: '/formEtudiant',
    component: FormEtudiant
  },
  {
    path: '/formMoniteur',
    component: FormMoniteur
  },
  {
    path: '/formSuperviseur',
    component: FormSuperviseur
  },
  {
    path: '/login',
    component: Login
  },
  {
    path: '/accountDetails',
    component: AccountDetails
  },
  {
    path: '/home',
    component: Home
  }



]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
