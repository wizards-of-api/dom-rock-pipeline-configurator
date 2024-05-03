import { createRouter, createWebHistory } from 'vue-router'
import ListView from '../views/ListView.vue'
import SandboxView from '@/views/SandboxView.vue'
import LZConfigView from '@/views/LZConfigView.vue'
import HomeView from '@/views/HomeView.vue'
import NotFound from '@/views/NotFound.vue'
<<<<<<< HEAD
import BronzeListView from '@/views/BronzeListView.vue'
=======
import BronzeConfigView from '@/views/BronzeConfigView.vue'
>>>>>>> fa2b1094922c3b7b12c9671d3a79d002c2b375c4

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
			path: '/bronze-config',
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
