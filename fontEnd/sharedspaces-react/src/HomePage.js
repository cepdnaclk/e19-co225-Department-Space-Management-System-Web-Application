import Hero from "./components/Hero";
import SechduleManager from "./components/ScheduleManager";
import styles from "./styles/Home.module.scss";
import { FiMapPin } from "react-icons/fi";
import { LuCalendarDays } from "react-icons/lu";
import { FaRegClock } from "react-icons/fa";
import { useState } from "react";
import classNames from "classnames";
const HomePage = () => {
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  return (
    <div>
      <Hero
        title="SharedSpaces"
        description="Check Availability and Reserve Spaces"
      >
        <div className={styles.actionBar}>
          <button
            className={styles.actionBtn}
            onClick={() => setIsMenuOpen(true)}
          >
            <FiMapPin />
            Any Space
          </button>
          <button className={styles.actionBtn}>
            <LuCalendarDays />
            Any Day
          </button>
          <button className={styles.actionBtn}>
            <FaRegClock />
            Any Time
          </button>
        </div>

        <div
          className={classNames(styles.menu, isMenuOpen && styles.active)}
        ></div>
      </Hero>
      <SechduleManager />
    </div>
  );
};

export default HomePage;
