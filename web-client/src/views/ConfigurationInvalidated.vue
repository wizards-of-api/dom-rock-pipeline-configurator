<template>
	<div class="modal">
		<div class="modal-content">
			<p class="config-title">Configuração invalidada!</p>
			<div class="divider"></div>
			<p class="config-message">
				Essa configuração não ficará disponível para a etapa Silver por conter ao menos uma
				coluna invalidada ou não ter hash. Deseja salvar mesmo assim?
			</p>
			<div class="button-container">
				<button class="button-style right-button" @click="cancel">
					Continuar editando
				</button>
				<button class="button-style left-button" @click="save" :disabled="!hasHash">Salvar</button>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	props: {
		hasHash: {
			type: Boolean,
			required: true,
		},
	},
	methods: {
		cancel() {
			this.$emit('close')
		},
		save() {
			if (this.hasHash) {
				this.$emit('saved')
				window.location.href = '/list-view-bronze'
			} else {
				alert("Selecione pelo menos um hash antes de salvar.")
			}
		},
	},
}
</script>



<style scoped>
@font-face {
	font-family: 'Poppins';
	src: url('src/assets/fonts/Poppins-Regular.ttf') format('truetype');
}

.modal {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
	background-color: rgba(0, 0, 0, 0.5);
}

.modal-content {
	width: 100%;
	max-width: 800px;
	background-color: #141416;
	padding: 20px;
	border-radius: 5px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
}

.config-title,
.config-message {
	font-family: 'Poppins', sans-serif;
	font-style: normal;
	font-weight: 400;
	margin-bottom: 15px;
	color: #c1bccc;
}

.config-title {
	font-size: 20px;
	line-height: 28px;
}

.config-message {
	font-size: 18px;
	line-height: 26px;
	margin-bottom: 25px;
}

.divider {
	border-bottom: 1px solid #c1bccc;
	margin-bottom: 15px;
}

.button-container {
	display: flex;
	justify-content: space-between;
}

.right-button {
	background: #00b4f0;
}

.left-button {
	background: #64c073;
}

.button-style {
	border-radius: 8px;
	font-family: 'Poppins', sans-serif;
	font-style: normal;
	font-weight: 400;
	font-size: 20px;
	line-height: 26px;
	color: #141416;
	padding: 15px 40px;
	border: none;
	cursor: pointer;
}
</style>
