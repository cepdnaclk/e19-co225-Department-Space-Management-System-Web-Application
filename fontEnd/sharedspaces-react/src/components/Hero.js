import styles from "../styles/Hero.module.scss";
const Hero = ({ title, description, children }) => {
  return (
    <div className={styles.Hero}>
      <h1>{title}</h1>
      <p>{description}</p>
      {children}
    </div>
  );
};

export default Hero;
