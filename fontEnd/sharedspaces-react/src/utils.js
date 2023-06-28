export const generateColorCode = (letter) => {
  const letters = "abcdefghijklmnopqrstuvwxyz";
  const index = letters.indexOf(letter.toLowerCase());
  if (index < 0) {
    throw new Error("Invalid letter");
  }

  //13.846
  const hue = (index * 30) % 360; // Generate hues based on the position of the letter in the alphabet
  return `hsl(${hue}, 30%, 55%)`; // Use HSL colors to generate vibrant and unique colors
};

export const getDateInFormat = (date) => {
  // Return Date in : "weekday, Month Day" format
  const dateOptions = {
    weekday: "long",
    month: "long",
    day: "numeric",
  };

  return new Intl.DateTimeFormat("en-US", dateOptions).format(date);
};

export const getTimeString = (time) => {
  const hour = Math.floor(time / 100);
  const minutes = time % 100;
  return new Intl.DateTimeFormat("en-US", {
    hour: "numeric",
    minute: "numeric",
  }).format(
    new Date(
      `2000-01-01T${hour.toString().padStart(2, "0")}:${minutes
        .toString()
        .padStart(2, "0")}`
    )
  );
};

export const setTimeFormat = (time) =>{
  const date = new Date('January 1,2022 ${time}');
  
  const hour = date.getHours();
  const minutes = date.getMinutes();

  const formattedTime = hour*100 + minutes;

  return formattedTime;
}