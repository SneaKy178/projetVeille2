import { createRouter, createWebHistory } from 'vue-router'
import FormEtudiant from '../components/Forms/FormEtudiant.vue'
import FormMoniteur from '../components/Forms/FormMoniteur.vue'
import FormSuperviseur from '../components/Forms/FormSuperviseur.vue'
import FormOffres from '../components/Forms/FormOffres'
import Login from '../components/Login.vue'
import AccountDetails from '../components/AccountDetails.vue'
import DnD from '../components/DnDCv.vue'
import DashboardSuperviseur from '../components/Dashboard/DashboardSuperviseur.vue'
import DashboardEtudiant from '../components/Dashboard/DasboardEtudiant/DashboardEtudiant.vue'
import DashboardMoniteur from '../components/Dashboard/DashboardMoniteur/DashboardMoniteur.vue'
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
    path: '/formOffres',
    component: FormOffres
  },
  {
    path: '/dndCv',
    component: DnD
  },
  {
    path: '/dashboardSuperviseur',
    component: DashboardSuperviseur
  },
  {
    path: '/dashboardEtudiant',
    component: DashboardEtudiant
  },
  {
    path: '/dashboardMoniteur',
    component: DashboardMoniteur
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
