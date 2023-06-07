import styles from "../styles/ScheduleManager.module.scss";
import * as React from "react";
const SechduleManager = ({ availableSpaces }) => {
  return (
    <div className={styles.container}>
      <AvailableSpaces availableSpaces={availableSpaces} />
      <div></div>
    </div>
  );
};

export default SechduleManager;

const AvailableSpaces = ({ availableSpaces }) => {
  const [select, setSelect] = React.useState(0);
  const handleClick = (id) => {
    //if already selected, then show more details on the space
    if (select === id) {
      console.log("TODO: Open modal");
    } else setSelect(id);
  };
  return (
    <div className={styles.AvailableSpaces}>
      <h3 className={styles.heading}>Available Spaces</h3>
      <div className={styles.SpaceContainer}>
        {availableSpaces.map((space) => {
          return (
            <Space
              key={space.id}
              space={space}
              select={select}
              handleClick={handleClick}
            />
          );
        })}
      </div>
    </div>
  );
};

const Space = ({ space, select, handleClick }) => {
  return (
    <div
      //key={space.id}
      id={space.id}
      className={select === space.id ? styles.SpaceSelected : styles.Space}
      onClick={() => handleClick(space.id)}
    >
      <div className={styles.name}>
        <h4>{space.name}</h4>
        <p>{space.description}</p>
      </div>
    </div>
  );
};
