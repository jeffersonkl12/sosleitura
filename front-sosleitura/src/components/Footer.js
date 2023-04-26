import "./footer.css";
import InputIcon from "./input/InputIcon";
import ArrowForwardIcon from "@mui/icons-material/ArrowForward";
import TwitterIcon from "@mui/icons-material/Twitter";
import InstagramIcon from "@mui/icons-material/Instagram";
import FacebookIcon from "@mui/icons-material/Facebook";
import GoogleIcon from "@mui/icons-material/Google";

export default function Footer() {
  return (
    <footer id="footer">
      <div className="footer-container">
        <div className="footer__top-content">
          <div className="top-content__card">
            <h4 className="card__titulo">SOSLEITURA</h4>
            <div className="card__content">
              <p>
                Obtenha 10% de desconto com notificações sobre as últimas
                notícias e atualizações.
              </p>
            </div>
          </div>
          <div className="top-content__card">
            <h4 className="card__titulo">Boletim de Notícias</h4>
            <div className="card__content">
              <InputIcon
                placeholder={"digite seu email..."}
                secondary={true}
                icon={ArrowForwardIcon}
                type={"email"}
              />
            </div>
          </div>
          <div className="top-content__card">
            <h4 className="card__titulo">Endereço</h4>
            <div className="card__content">
              <p>
                319 Clematis St.
                <br />
                Suite 100 WPB, FL 33401
              </p>
            </div>
          </div>
          <div className="top-content__card">
            <h4 className="card__titulo">Siganos</h4>
            <div className="card__content">
              <ul className="card__lista-icon flex f-ai-c">
                <li>
                  <a>
                    <TwitterIcon />
                  </a>
                </li>
                <li>
                  <a>
                    <InstagramIcon />
                  </a>
                </li>
                <li>
                  <a>
                    <FacebookIcon />
                  </a>
                </li>
                <li>
                  <a>
                    <GoogleIcon />
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <div className="footer__down-content">
          <div className="down-content__direitos">
            <p>2023 - Todos Direitos Reservados</p>
          </div>
          <div className="down-content__lista-link">
            <ul className="lista-link__content flex f-ai-c">
              <li>
                <a>Home</a>
              </li>
              <li>
                <a>Produto</a>
              </li>
              <li>
                <a>Contatos</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </footer>
  );
}
