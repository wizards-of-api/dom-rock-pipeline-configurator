import { createRouter, createWebHistory } from 'vue-router'
import ListView from '../views/ListView.vue'
import SandboxView from '@/views/SandboxView.vue'
import LZConfigView from '@/views/LZConfigView.vue'

const router = createRouter({
	history: createWebHistory(import.meta.env.BASE_URL),
	routes: [
		{
			path: '/',
			name: 'list-view',
			component: ListView,
		},
		{
			path: '/list-view',
			name: 'list-view',
			component: ListView,
		},
		{
			path: '/lz-config',
			name: 'lz-config',
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
	],
})

export default router
