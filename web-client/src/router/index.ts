import { createRouter, createWebHistory } from 'vue-router'
import ListView from '../views/ListView.vue'
import SandboxView from '@/views/SandboxView.vue'
import LZConfigView from '@/views/LZConfigView.vue'
import HomeView from '@/views/HomeView.vue'
import AdminHomeView from '@/views/AdminHomeView.vue'
import NotFound from '@/views/NotFound.vue'
import BronzeListView from '@/views/BronzeListView.vue'
import BronzeConfigView from '@/views/BronzeConfigView.vue'
import SilverConfigView from '@/views/SilverConfigView.vue'
import SilverListView from '@/views/SilverListView.vue'
import LZConfigUpdateView from '@/views/LZConfigUpdateView.vue'
import CadastroInputSection from '@/components/cadastro-config/CadastroInputSection.vue'
import CreateUserView from '@/views/CreateUserView.vue'
import LoginView from '@/views/LoginView.vue'

const router = createRouter({
	history: createWebHistory(import.meta.env.BASE_URL),
	routes: [
		{
			path: '/home',
			name: 'HomeView',
			component: HomeView,
		},
		{
			path: '/admin-home',
			name: 'Administrador',
			component: AdminHomeView,
		},
		{
			path: '/list-view',
			name: 'ListView',
			component: ListView,
		},
		{
			path: '/bronze-config/:id',
			name: 'BronzeConfigView',
			component: BronzeConfigView,
		},
		{
			path: '/lz-config',
			name: 'LZConfigView',
			component: LZConfigView,
		},
		{
			path: '/lz-config/update/:id',
			name: 'LZConfigUpdateView',
			component: LZConfigUpdateView,
		},
		{
			path: '/list-view-bronze',
			name: 'BronzeListView',
			component: BronzeListView,
		},
		{
			path: '/list-view-silver',
			name: 'SilverListView',
			component: SilverListView,
		},
		{
			path: '/sandbox',
			name: 'sandbox',
			component: SandboxView,
		},
		{
			path: '/:pathMatch(.*)*',
			name: 'NotFound',
			component: NotFound,
		},
		{
			path: '/silver-config/:id',
			name: 'SilverConfigView',
			component: SilverConfigView,
		},
		{
			path: '/user-register',
			name: 'UserRegister',
			component: CreateUserView,
			path: '/login',
			name: 'LoginView',
			component: LoginView,
		},
	],
})

export default router
