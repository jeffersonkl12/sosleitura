import "./header.css";
import SearchIcon from '@mui/icons-material/Search';
import PersonIcon from '@mui/icons-material/Person';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import MenuIcon from '@mui/icons-material/Menu';
import CloseIcon from '@mui/icons-material/Close';
import {TextField } from "@mui/material";
import styled from "@emotion/styled";
import { useState, useEffect } from "react";

const IconClose = styled(CloseIcon)(() => ({
    color: "white",
    transition: "ease",
    transitionDuration: "0.2s",
    transformOrigin: "center",
    ":hover": {
        color: "red"
    }
}));

const IconSearch = styled(SearchIcon)(() => ({
    color: "var(--cinza-escuro)",
    transition: "ease",
    transitionDuration: "0.2s",
    ":hover": {
        color: "white"
    }
}));

const navItem = ["Home", "Mais", "Blog", "Loja", "Paginas", "Contato"];

function Header() {

    const [iconSearch, setIconSearch] = useState(false);
    const [itemAtivado, setItemAtivado] = useState(Array(navItem.length).fill(false));

    useEffect(() => {
        let navLinks = [...itemAtivado];
        navLinks[0] = true;
        setItemAtivado(navLinks);
    }, []);

    function clickBarraPesquisa(valor) {
        setIconSearch(valor);
    }

    function clickNavItem(index) {
        if (!itemAtivado[index]) {
            let itemAtivadoCopy = [...itemAtivado].fill(false);
            itemAtivadoCopy[index] = true;
            setItemAtivado(itemAtivadoCopy);
        }

    }

    return (
        <header id="header">
            <div className="hedaer__container flex f-jc-se f-ai-c">
                <h1 className="header__logo">SOSLEITURA</h1>
                <nav className="header__nav nav-lista">
                    <ul className="lista__container flex">
                        {navItem.map((item, index) => {
                            return <li key={index}>
                                <a className={`nav-lista__item${itemAtivado[index] ? " ativado" : ""}`}
                                    onClick={() => clickNavItem(index)}>{item}</a>
                            </li>
                        })}
                    </ul>
                </nav>
                <div className="header__icon">
                    <ul className="icon__lista flex">
                        <li>
                            <a className="flex f-ai-c">
                                <SearchIcon onClick={() => clickBarraPesquisa(true)} fontSize="medium" />
                            </a>
                        </li>
                        <li>
                            <a className="flex f-ai-c">
                                <PersonIcon fontSize="medium" />
                            </a>
                        </li>
                        <li>
                            <a className="flex f-ai-c">
                                <ShoppingCartIcon fontSize="medium" />
                            </a>
                        </li>
                        <li>
                            <a className="flex f-ai-c">
                                <MenuIcon />
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            {iconSearch ? <div className="header__pesquisa">
                <form className="pesquisa__form" action="#">
                    <div className="pesquisa__field flex">
                        <TextField fullWidth label="" id="fullWidth" placeholder="digite a pesquissa.."
                            sx={{
                                backgroundColor: "white",
                                "& .css-1d3z3hw-MuiOutlinedInput-notchedOutline": {
                                    border: "none"
                                }
                            }}
                            inputProps={{
                                style: {
                                    fontSize: "20px"
                                }
                            }} />
                        <div className="pesquisa__icon flex f-ai-c f-jc-c">
                            <IconSearch fontSize="large" />
                        </div>
                        <div className="pesquisa__icon-item flex f-ai-c f-jc-c">
                            <IconClose onClick={() => clickBarraPesquisa(false)} fontSize="medium" />
                        </div>
                    </div>
                </form>
            </div> : null}
        </header>)

};

export default Header;