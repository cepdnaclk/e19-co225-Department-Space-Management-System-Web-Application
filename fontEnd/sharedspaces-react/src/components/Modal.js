import React, { useState, useRef } from "react";
import { createPortal } from "react-dom";
import styles from "../styles/Modal.module.scss";
const Modal = ({ setIsOpen, isOpen, rect }) => {
  const posLeft = {
    left: `calc(${rect.left}px + ${rect.width}px + 5dvh)`,
  };

  const posRight = {
    right: `calc(100vw - ${rect.right}px + 30dvh)`,
  };

  const posTop = {
    top: `calc(${rect.top}px + ${window.scrollY}px - 10%)`,
  };

  const posBottom = {
    bottom: `calc(100vh - ${rect.bottom}px)`,
  };

  const posX = (rect.left / window.innerWidth) * 100 > 50 ? posRight : posLeft;
  const posY =
    (rect.top / document.documentElement.clientHeight) * 100 > 65
      ? posBottom
      : posTop;

  return createPortal(
    <div className={styles.container} id="modal" style={{ ...posX, ...posY }}>
      <AddEvent />
    </div>,
    document.getElementById("portal")
  );
};

export default Modal;

const AddEvent = () => {
  return <div></div>;
};
