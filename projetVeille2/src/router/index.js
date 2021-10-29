import { createRouter, createWebHistory } from 'vue-router'
import SignupForm from '../components/SignupForm.vue'
import FormEtudiant from '../components/Forms/FormEtudiant.vue'
import FormMoniteur from '../components/Forms/FormMoniteur.vue'
import Home from '../components/Home.vue'

const routes = [
  {
    path : '/',
    component: Home
  },
  {
    path: '/signupForm',
    component: SignupForm
  },
  {
    path: '/formEtudiant',
    component: FormEtudiant
  },
  {
    path: '/formMoniteur',
    component: FormMoniteur
  }


]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
