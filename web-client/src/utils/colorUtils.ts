export function getRandomColor(): string {
	const letters = '0123456789ABCDEF';
	let color = '#';
	for (let i = 0; i < 6; i++) {
		color += letters[Math.floor(Math.random() * 16)];
	}
	return color
}

export function generateColors(numColors: number): string[] {
	const colors = [];
	for (let i = 0; i < numColors; i++) {
		colors.push(getRandomColor());
	}
	return colors
}
