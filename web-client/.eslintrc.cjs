/* eslint-env node */
require('@rushstack/eslint-patch/modern-module-resolution')

module.exports = {
	root: true,
	'plugins': [
		'@stylistic/js',
	],
	'extends': [
		'plugin:vue/vue3-essential',
		'eslint:recommended',
		'@vue/eslint-config-typescript',
		'@vue/eslint-config-prettier/skip-formatting',
	],
	parserOptions: {
		ecmaVersion: 'latest',
	},
	rules: {
		'@stylistic/js/comma-dangle': ['error', 'always-multiline'],
		'@stylistic/js/eol-last': ['error', 'always'],
		'@stylistic/js/semi': ['warn', 'never'],
		'@stylistic/js/indent': ['warn', 'tab'],
	},
}
