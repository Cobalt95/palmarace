import "./Spinner.scss";

const Spinner = () => {
    return (
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 50 50" width="100" height="100">
            <circle className="spinning-circle" cx="25" cy="25" r="20" stroke-width="5" fill="none" opacity="0.3" />
            <circle className="spinning-circle" cx="25" cy="25" r="20" stroke-width="5" fill="none" stroke-linecap="round"
                stroke-dasharray="31.4 94.2">
                <animateTransform 
                    attributeName="transform" 
                    type="rotate" 
                    from="0 25 25" 
                    to="360 25 25" 
                    dur="1s" 
                    repeatCount="indefinite" 
            />
            </circle>
        </svg>
    )
};

export default Spinner;