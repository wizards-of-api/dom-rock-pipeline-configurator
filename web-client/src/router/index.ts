import { createRouter, createWebHistory } from 'vue-router'
import ListView from '../views/ListView.vue'
import SandboxView from '@/views/SandboxView.vue'
import LZConfigView from '@/views/LZConfigView.vue'
import HomeView from '@/views/HomeView.vue'
import NotFound from '@/views/NotFound.vue'

const router = createRouter({
	history: createWebHistory(import.meta.env.BASE_URL),
	routes: [
		{
			path: '',
			name: 'Home',
			component: HomeView,
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
			path: '/lz-config',
			name: 'Configuração de arquivo Landing Zone',
			// route level code-splitting
			// this generates a separate chunk (About.[hash].js) for this route
			// which is lazy-loaded when the route is visited.
			// component: () => import('../views/LZConfigView.vue'),
			component: LZConfigView,
		},
		{
			path: '/sandbox',
			name: 'sandbox',
			component: SandboxView,
		},
		{
			path: '/:pathMatch(.*)*',
			name: 'sandbox',
			component: NotFound,
		},
	],
})

export default router
