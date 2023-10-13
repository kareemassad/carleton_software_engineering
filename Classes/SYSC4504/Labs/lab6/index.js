import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

const Lab06App = function (props) {
  return (
    <main>
      <Title label="Lab06 -- React Application" />
      <Catalog />
    </main>
  );
};

const Title = function (props) {
  return (
    <h1>{props.label}</h1>
  );
};

class Catalog extends React.Component {
  constructor(props) {
    super(props);
    this.handleNameChange = this.handleNameChange.bind(this);
    this.handleAltChange = this.handleAltChange.bind(this);
    //init state of filename to "images/img1.jpg"
    this.state = { filename: "images/img1.jpg", alt: "image 1", editing: false, selectedFilename: "images/img1.jpg" };
    // init alt to image 1
  }
  renderNormal() {
    return (
      <div>
        {/* h2 return current alt value */}
        <h2>{this.state.alt}</h2>
        <img src={this.state.selectedFilename} alt={this.state.alt} onClick={this.editClick} ></img>
      </div>
    );
  };

  renderEdit() {
    return (
      <div>
        <p> File Name:
          {/* drop down menu with select/options, were options are paths */}
          <select value={this.state.selectedFilename} onChange={this.handleNameChange}>
            <option value="images/img1.jpg" >image 1</option>
            <option value="images/img2.jpg" >image 2</option>
            <option value="images/img3.jpg" >image 3</option>
            <option value="images/img4.jpg" >image 4</option>
          </select>
        </p>
        <p> Alt Text:
          {/* input text, takes alt text */}
          <input type="text" value={this.state.alt} onChange={this.handleAltChange} ></input>
        </p>
        {/* save button */}
        <button onClick={this.saveClick}>Save</button>
      </div>
    );
  }

  editClick = () => {
    this.setState({ editing: true });
  };

  saveClick = () => {
    this.setState({ editing: false, filename: this.state.filename, alt: this.state.alt });
  };

  render() {
    if (this.state.editing) {
      return (this.renderEdit());
    } else {
      return (this.renderNormal());
    }
  }

  handleNameChange(event) {
    this.setState({ selectedFilename: event.target.value });
  }

  handleAltChange(event) {
    this.setState({ alt: event.target.value });
  }
}

const root = ReactDOM.createRoot(document.getElementById('react-lab'));
root.render(
  <React.StrictMode>
    <Lab06App />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
