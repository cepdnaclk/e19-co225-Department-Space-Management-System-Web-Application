import jwt_decode from "jwt-decode";
import { Role } from "./data";

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

export const getDateInYearFormat = (date) => {
  return date.toLocaleDateString("sv-SE", { timeZone: "Asia/Colombo" });
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

export const setTimeFormat = (time) => {
  const date = new Date(`January 1,2022 ${time}`);

  const hour = date.getHours();
  const minutes = date.getMinutes();

  return hour * 100 + minutes;
};

export const mapTimeStringToInteger = (timeString) => {
  /*
    >>> "9:00PM"
    2100

    >>> "9:30AM"
    2130

    >>> " 10:20 AM "
    1020

    >>> "12:30 PM"
    1230
 
 */
  const timeRegex = /^\s*(\d{1,2}):(\d{2})\s*(am|pm)\s*$/i;

  const match = timeString.match(timeRegex);

  if (!match) {
    return false;
  }

  let hour = parseInt(match[1], 10);
  const minute = parseInt(match[2], 10);
  const suffix = match[3].toLowerCase();

  if (hour < 1 || hour > 12) {
    return false;
  }

  if (minute < 0 || minute > 59) {
    return false;
  }

  //if 12:30PM --> 1230 not 2430
  if (suffix === "pm" && hour < 12) {
    hour += 12;
  }

  // console.log(hour*100 + minute)
  return hour * 100 + minute;
};

export function checkUser(setUser, setValid, handleLogout) {
  const token = localStorage.getItem("token");

  if (token == null) {
    setValid(false);
    setUser("");
  } else {
    const userDetails = jwt_decode(token);

    if (userDetails.exp * 1000 < Date.now()) {
      handleLogout();
    } else {
      const user = userDetails.user;

      if (userDetails.role === "responsible_person") {
        user.role = Role.RESPONSIBLE;
      } else if (userDetails.role === "user") {
        user.role = Role.USER;
      } else {
        user.role = Role.ADMIN;
      }

      setValid(true);
      setUser(user);
    }
  }
}
