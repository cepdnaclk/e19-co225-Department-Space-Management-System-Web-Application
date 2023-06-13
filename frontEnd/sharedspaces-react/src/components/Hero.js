import styles from "../styles/Hero.module.scss";
const Hero = ({ title, description }) => {
  return (
    <div className={styles.Hero}>
      <h1>{title}</h1>
      <p>{description}</p>
    </div>
  );
};

export default Hero;
