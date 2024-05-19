import { createRouter, createWebHistory } from 'vue-router'
import ListView from '../views/ListView.vue'
import SandboxView from '@/views/SandboxView.vue'
import LZConfigView from '@/views/LZConfigView.vue'
import HomeView from '@/views/HomeView.vue'
import NotFound from '@/views/NotFound.vue'
import BronzeListView from '@/views/BronzeListView.vue'
import BronzeConfigView from '@/views/BronzeConfigView.vue'
import LZConfigUpdateView from '@/views/LZConfigUpdateView.vue'

const router = createRouter({
	history: createWebHistory(import.meta.env.BASE_URL),
	routes: [
		{
			path: '/home',
			name: 'HomeView',
			component: HomeView,
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
