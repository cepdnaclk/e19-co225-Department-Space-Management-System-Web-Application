export const generateColorCode = (letter) => {
  const letters = "abcdefghijklmnopqrstuvwxyz";
  const index = letters.indexOf(letter.toLowerCase());
  if (index < 0) {
    throw new Error("Invalid letter");
  }
  const hue = (index * 13.846) % 360; // Generate hues based on the position of the letter in the alphabet
  return `hsl(${hue}, 35%, 60%)`; // Use HSL colors to generate vibrant and unique colors
};
