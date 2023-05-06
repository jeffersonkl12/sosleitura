import { useRouteError } from "react-router-dom";
import LoginField from "../LoginField";

export default function LoginErrorHandler(){
    const error = useRouteError();
    
    if(error.status === 401){
        return (<LoginField statusError={true}/>);
    }
     

}