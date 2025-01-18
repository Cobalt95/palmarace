import "./Footer.scss";

const Footer = () => {

    // state
    // handlers
    // render
    return (
        <div className="footer-container">
            <div>
                <h3>Useful links</h3>
                <ul>
                    <li><a>About</a></li>
                    <li><a>Contact</a></li>
                    <li><a>Careers</a></li>
                </ul>
            </div>
            <div>
                <h3>Help</h3>
                <ul>
                    <li><a>Privacy policies</a></li>
                    <li><a>Terms of use</a></li>
                    <li><a>Accessibility</a></li>
                </ul>
            </div>
            <div>
                <h3>Documentation</h3>
                <ul>
                    <li><a>Home</a></li>
                    <li><a>Sitemap</a></li>
                    <li><a>French</a></li>
                    <li>© 2025 Palmarace</li>
                </ul>
            </div>
        </div>
    );
}

export default Footer;