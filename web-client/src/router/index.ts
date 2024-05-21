import { createRouter, createWebHistory } from 'vue-router'
import ListView from '../views/ListView.vue'
import SandboxView from '@/views/SandboxView.vue'
import LZConfigView from '@/views/LZConfigView.vue'
import HomeView from '@/views/HomeView.vue'
import AdminHomeView from '@/views/AdminHomeView.vue'
import NotFound from '@/views/NotFound.vue'
import BronzeListView from '@/views/BronzeListView.vue'
import BronzeConfigView from '@/views/BronzeConfigView.vue'
import SilverListView from '@/views/SilverListView.vue'
const router = createRouter({
	history: createWebHistory(import.meta.env.BASE_URL),
	routes: [
		{
			path: '',
			name: 'Home',
			component: HomeView,
		},
		{
			path: '/home',
			name: 'Home',
			component: HomeView,
		},
		{
			path: '/admin-home',
			name: 'Administrador',
			component: AdminHomeView,
		},
		{
			path: '/list-view',
			name: 'Configurações Landing Zone',
			component: ListView,
		},
		{
			path: '/list-view',
			name: 'list-view',
			component: ListView,
		},
		{
			path: '/bronze-config/:id',
			name: 'Etapa Bronze',
			component: BronzeConfigView,
		},
		{
			path: '/lz-config',
			name: 'Etapa Landing Zone',
			// route level code-splitting
			// this generates a separate chunk (About.[hash].js) for this route
			// which is lazy-loaded when the route is visited.
			// component: () => import('../views/LZConfigView.vue'),
			component: LZConfigView,
		},
		{
			path: '/list-view-bronze',
			name: 'Configurações Bronze',
			component: BronzeListView,
		},
		{
			path: '/list-view-silver',
			name: 'Configurações Silver',
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
	],
})

export default router
