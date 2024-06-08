<script setup lang="ts">
import DRButton from '@/components/DRButton.vue'

const login = () => {
	console.log("Welcome")
}
</script>
<template>
  <main>
  <div class="login_container">
    <img src="../assets/dom-rock-logo.png">
    <div class="input_email">
      <h3>Usuário</h3>
      <input type="text" class="email" placeholder="Usuário">
    </div>
    <div class="input_password">
      <h3>Senha</h3>
      <input type="password" class="password" placeholder="Senha">
    </div>
    <div class="button_login">
      <DRButton :click-behavior="login" size="large">Entrar</DRButton>
    </div>
  </div>
  </main>
  <footer>
    <div class="copyright">
      <p>© 2024 Wiz. Todos os direitos reservados.</p>
    </div>
  </footer>
</template>
<style scoped lang="scss">
main {
	margin-top:10%;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content:center;
	gap:10px;
}
.login_container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

input{
  width: 410px;
  height: 50px;
  border-radius: 8px;
  border: 1px solid #ffffff;
  padding: 5px;
}

.input_email, .input_password {
  margin-top:10px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  gap:10px;
}

.button_login {
  margin-top:30px;
  display: flex;
  height:400px;
  flex-direction: column;
  align-items: center;
  gap:10px;
}

.copyright {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom:20px;
}


</style>
