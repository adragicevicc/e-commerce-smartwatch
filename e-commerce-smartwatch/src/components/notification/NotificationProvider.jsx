import {v4} from "uuid";
import Notification from "./Notification";
import { createContext, useContext, useReducer, useState } from "react";
import styled from 'styled-components';

export const NotificationContext = createContext();

const NotificationWrapper = styled.div`
    position: fixed;
    top: 10px;
    right: 10px;
    width: 400px;
`;

const NotificationProvider = (props) => {

  const [state, dispatch] = useReducer((state, action) => {
    switch (action.type){
      case "ADD_NOTIFICATION":
        return [...state, {...action.payload}];
      case "REMOVE_NOTIFICATION":
        return state.filter(el => el.id !== action.id);
      default:
          return state;
    }
  }, [] );


  return (
    <NotificationContext.Provider value={dispatch}>
      <NotificationWrapper>
        {state.map(note => {
          return <Notification dispatch={dispatch} key={note.id} { ...note }/>
        })}
      </NotificationWrapper>
        {props.children}
    </NotificationContext.Provider>
  )
}

export const useNotification = () => {
  const dispatch = useContext(NotificationContext)

  return (props) => {
    dispatch({
      type: "ADD_NOTIFICATION",
      payload: {
        id: v4(),
        ...props
      }
    })
  }
}

export default NotificationProvider