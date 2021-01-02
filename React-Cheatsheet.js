// ReactJS Basics - #9 How does ReactJS update the DOM?
https://www.youtube.com/watch?v=Iw2BLUjQo1E

//How to optimize a React JS app bundle performance to load under 3s | Code splitting | Lazy loading
https://www.youtube.com/watch?v=j8NJc60H294

//---------------------Routing------------------------
	$ npm i react-router-dom
	
	// index.js
	import { BrowserRouter } from 'react-router-dom';
	
	ReactDOM.render(
		// BrowserRouter wraps history object of browser and passes it down to component tree.
		<BrowserRouter>
			<App />
		</BrowserRouter>
	);
	
	//App.js
	import { Route, Switch } from 'react-router-dom';
	import React, {component} from 'react';
	
	class App extends Component {
		render() {
			return (
				<div>
				<Switch>
					<Route path="/products" component={Products} />
					<Route path="/posts" component={Posts} />
					<Route path="/admin" component={Dashboard} />
					<Route path="/" component={Home} />           // use "exact" if not using switch
				<Switch>
				</div>
			)
		}
	}
	
	// Link
		// By default whenever you navigate from one page to another the entire page relods including bunde.js
		// Whereas in SPAs only individual component area should be reloaded. We can fix this by replacing <a> with <Link>
	import React from "react";
	import { Link } from 'react-router-dom';
	
	const NavBar = () => {
		return (
			<ul>
				<li><Link to="/">Home</Link></li>
				<li><Link to="/products">Home</Link></li>
				<li><Link to={ pathname: "/admin", state: {from: "root"} } >Home</Link></li>
			<ul>
		);
	}
	
	// ---------------------Route Props---------------------
	// The Route component injects 3 props if the pattern matched
	// 1. History: to work with history object of browser and with that we can send user to different page.
	// 2. Match: constains information about how this url match the path we set in route{params:{...},pathname:"/products",url:"/products"}
	// 3. Location: Represent where the app sits now. { pathname: "/products", search: "?sort=asc&approved=true"}
	
	// Passing additional Props
	class App extends Component {
		render() {
			return (
				<div>
				<Switch>
					// we pass props to pass default props (location, match, history)
					<Route path="/products" render={(props) => <Products sortBy="newest" {...props}/> } />
					<Route path="/posts" component={Posts} />
					<Route path="/admin" component={Dashboard} />
					<Route path="/" component={Home} />
				<Switch>
				</div>
			)
		}
	}
	
	//---------------------Rote Params/Path Params---------------------
		class App extends Component {
			render() {
				return (
					<div>
					<BrowserRouter>
						<Switch>
							<Route path="/products/:id" component={ProductDetails} />
							<Route path="/products" render={(props) => <Products sortBy="newest" {...props}/> } />
						<Switch>
					<BrowserRouter>
					</div>
				)
			}
		}
		//ProductDetails.jsx
		this.props.match.params.id
		
		//  Optional Route params
		//  to work with param that is fixed not to exact match optional part
		<Route path="/products/:year?/:month?" component={ProductDetails} />
	
	//---------------------Query String---------------------
		// ?sort=asc&approved=true
		props.location.search
		
		// use 3rd party library 
		$ npm i query-string
		import queryString from 'query-string';
		
		const abc = ({location}) =>{
			const { sort, approved } = queryString.parse(location.search);
		};
		
	//---------------------Redirect---------------------	
	import { Route, Switch, Redirect } from 'react-router-dom';
	import React, {component} from 'react';
	
	class App extends Component {
		render() {
			return (
				<div>
				<Switch>
					<Route path="/products" component={Products} />
					<Redirect from="messages" to="posts">
					<Route path="not-found" component={NotFound}>
					<Route path="/" exact component={Home} />       // using exact as page which doesn't exists redirects to home because of "/"
					<Redirect to="/not-found">
				<Switch>
				</div>
			)
		}
	}
	//---------------------History------------------------
	// Programatic Navigation
	// history has many prperties like {goBack, goForward, push, replace}
	handleSave = () => {
		this.props.history.push("/products");
	}
	// Also used to override a page from history by replacing
	this.props.history.replace("/not-found");
	
//--------------------- Forms ------------------------
// ** Controlled Components
// In a controlled component, the form data is handled by the state within the component. 
// The state within the component serves as “the single source of truth” for the input elements that are rendered by the component.
// Controlled elements they dont have their own state 
// 	- they get all their data by props and 
//  - notify changes to data by raising events.
class LoginForm extends Component{
	state = {
		account: { username:'', password: '' }
	};
	handleSubmit = event = {
		event.preventDefault();
		// Call the server
		// const username = document.getElementById('username').value; 
			// never use document because in react the whole point is to put an abstraction above DOM
			// without directly using DOM it will beeasier for us to unit test
	}
	handleChange = event => {
		const account = {...this.state.account};
		//account.username = event.currentTarget.value;
		account[event.currentTarget.name] = event.currentTarget.value;
		this.setState({ account });
	}	
	render(
		return (
		<form onSubmit={this.handleSubmit}>
			// Input field has their own state hence must be used as controlled or refs in react
			<label htmlfor="exampleInputEmail"> Email Address </label>		// we use for so that after clicking the tag 	
			<input 															// input field gets focused
				value={this.state.account.username} 
				onChange={this.handleChange}
				name="username"
				type="Email" id="exampleInputEmail"
			/>					
			<button>Login</button>
		</form>
		)
	)
}
// *** For input fields can not set state to undefined or null it has to be "", or if using state properties which is not defined 
// we will face issue of Error: trying to change uncontrolled into controlled as react thinks when user types into it,
// you are trying to changeuncontrolled into controlled

// By Default HTML form makes a full round trip to server upon submition
// we can use onSubmit={}

// **Uncontrolled Components
// Uncontrolled components act more like traditional HTML form elements. 
// The data for each input element is stored in the DOM, not in the component. 
// Instead of writing an event handler for all of your state updates, you use a ref to retrieve values from the DOM.
// Using Refs should be minimized
// Only to access DOMs and animating by using DOMs
	constructor(props){
		super(props);
		this.username = React.createRef();
	}
	
	componentDidMount() {
		this.username.current.focus();
	}
	const handleSubmit = e => {
		e.preventDefault();
		
		const username = this.username.current.value;
	}
	return (
		<form onSubmit={this.handleSubmit}>
			<label htmlfor="exampleInputEmail"> Email Address </label>		// we use for so that after clicking the tag 	
			<input ref={this.username} type="Email" id="exampleInputEmail">					// input field gets focused
			<button>Login</button>
		</form>
	)
	
//--------------------- Form Validation on submit------------------------

	state = {
		accout: {username: "", password: ""},
		errors: {}
	};
	handleChange = e => {
		const account = {...this.state.account};
		account[e.currentTarget.name] = e.currentTarget.value;
		this.setState({ account });
	}
	validate = () => {
		const errors = {};
		const { account } = this.state;
		
		if(account.username.trim() === '')
			errors.username = 'Username is required';
		
		return Object.keys(errors).length == 0 ? null : errors;
	};
	handleSubmit = e => {
		e.preventDeafult();
		
		const errors = this.validate();
		this.setState({ errors: errors || {} });
		if(errors) return;
		
		//Call server
	}

	return (
		<form onSubmit={this.handleSubmit}>
			<label htmlfor="exampleInputEmail"> Email Address </label>			
			<input onChange={this.handleChange} type="Email" id="exampleInputEmail" name="username">
			{this.state.errors.username && <div>{this.state.errors.username}</div>}		
			<button>Login</button>
		</form>
	)


//--------------------- Form Validation on change------------------------
handleChange = {currentTarget: input} => {
	const errors = {...this.state.errors};
	const errorMessage = this.validateProperty(input);
	if(errorMessage) errors[input.name]=errorMessage;
	else delete errors[input.name];
		
	const account = {...this.state.account};
	account[input.name] = input.value;
	this.setState({ account, errors });
};
validateProperty = {name, value} => {
	if(name === "username"){
		if(value.trim() === '') return 'Username is required';
	}
	if(name === "password"){
		if(value.trim() === '') return 'Password is required';
	}
};
//-------------------Preserving data without re-rendering--------------
	function App() {
	  const [value, setValue] = React.useState("");
	  const valueRef = React.useRef();

	  const handleClick = e => {
		setValue(valueRef.current.value);
	  };

	  return (
		<div className="App">
		  <h4>Value: {value}</h4>
		  <input ref={valueRef} />
		  <Button onClick={handleClick}>Button</Button>
		</div>
	  );
	}
//-------------------------Pure Component------------------------------
// Instaed of pure component to stop rendering even if the state is same
class App extends Component {
	state = {
		val: 1
	}
	componentDidMount(){
		setInterval(() => {
			this.setState(() => {
				return {val: 1}
			});
		}, 2000)
	};
	shouldComponentUpdate(nextProp, nextState){
		return (this.state.value1 === nextState.value1 ? false : true);
	};
	render() {

	};
}
// Also can use pure component for same operation // ***Use shouldComponentUpdate over Pure as it does shallow compares
class App extends PureComponent {
	state = {
		val: 1
	}
	componentDidMount(){
		setInterval(() => {
			this.setState(() => {
				return {val: 1}
			});
		}, 2000)
	}
	render() {

	}
}

====================================Type Checking with Props Type===============================
import PropTypes from 'prop-types';

class Greeting extends React.Component {
  render() {
    return (
      <h1>Hello, {this.props.name}</h1>
    );
  }
}

Greeting.propTypes = {
  name: PropTypes.string
};

   // PropTypes.array,
   // PropTypes.bool,
   // PropTypes.func,
   // PropTypes.number,
   // PropTypes.object,
   // PropTypes.string,
   // PropTypes.symbol,
=======================================Hooks===========================================
--------------------------------------useState-----------------------------------------
// Use State
const [count, setCount] = useState('/*Initial State*/');
// If you want to update the state based on previous value use function
setCount(prevCount => prevCount + 1)
// we have to use as callback as setState is async
--------------------------------------useEffect-----------------------------------------
// Use Effect componentDidUpdate (used when you want side effect in your code)(side effect: API call, timer)
useEffect(() => { // For componentDidUpdate(prevProps, prevState)
	console.log('useEffect - Updating documrny title');
	documrny.title = `You clicked ${count} times`;
}, [count]);

// Mimic componentDidMount with useEffect
useEffect(() => { // For componentDidMount
	console.log('useEffect - Setting documrny title once');
	documrny.title = `You clicked ${count} times`;
}, []);

// Mimic componentWillUnmount with useEffect
const logMousePosition = e => {}
useEffect(() => { // Cleanup task in unmount
	return () =>{
		console.log('Component unmounting code'); 
		window.removeEventListener('mousemove', logMousePosition)
	}
})


// Data fetching with hooks
function DtaFetching() {
	const [posts, setPosts] = useState([])
	useEffect(() => {
		axios.get('https://jsonplaceholder.typicode.com/posts')
			.then(res => {
				console.log(res);
				setPosts(res.data);
			})
			.catch(err => {
				console.log(err);
			})
	},[]);
	return(
		<div>
			<ul>
				{posts.map(post => (
					<li key={post.id}>{post.title}</li>
				))}
			</ul>
		</div>
	)
}

function DataFetching() {
	const [post, setPost] = useState({})
	const [id, setId] = useState(1)
	const [idFromButtonClick, setIdFromButtonClick] = useState(1)
	const handleClick = () => {
		setIdFromButtonClick(id);
	}
	useEffect(() => {
		axios.get(`https://jsonplaceholder.typicode.com/${id}`)
		.then(res => {
			console.log(res)
			setPosts(res.data)
		})
		.catch(err => {
			console.log(err)
		})
	}, [idFromButtonClick]);
	return (
		<div>
			<input type="text" value={id} /*onChange={e => setId(e.target.value)}*/ />
			<button type="button" onClick={handleClick}>Fetch Post</button>
			<div>post.title</div>
		</div>
	)
}
--------------------------------------useContext---------------------------------------
// it is used to send data from one Component to other without passing data through components
// App.js
export const UserContext = React.createContext();
export const ChannelContext = React.createContext();
function App() {
	return (
		<div className='App'>
			<UserContext.Prrovider value={'Vishwas'}>
					<ChannelContext.Provider value={'Codevolution'}>
						<CoponentC />
					</ChannelContext.Provider>
			</UserContext.Provider>
		</div>
	)
}
export default App
// App.js --> ComponentC --> ComponentE --> ComponentF
// ComponentF.js
import {UserContext, ChannelContext} from '../App'
function ComponentF() {
	return (
		<div>
			<UserContext.Consumer>
				{user => { 
					return(
						<ChannelContext.Consumer>
							{channel => {return (<div>User context value {user}, channel value {channel}</div>)}}
						</ChannelContext.Consumer>
					)
				}}
			</UserContext.Consumer>
		</div>
	)
}
export dfault ComponentF
// Simpler using useContext Hoook
import { UserContext, ChannelContext } from './App'
function ComponentE() {
	const user = useContext(UserContext)
	const channel = useContext(ChannelContext)
	
	return (<div> {user} - {channel} </div>)
}
export default ComponentE
---------------------------------------useReducer--------------------------------------
function appReducer(state, action) {
	switch (action.type) {
		case 'increment':
			return {count: state.count+action.payload.step};
		case 'decrement':
			return {count: state.count-action.payload.step};
		default:
			return state;
	}
}
export defult function TodosApp() {
	const [state, dispatch] = useReducer(appReducer, { count: 0})
	
	function increment() {
		dispatch({ type: 'increment', payload: {step: 2} })
	}
	function decrement() {
		dispatch({ type: 'increment',  payload: {step: 3}})
	}
	return (
		Todos App
	);
}
--------------------------------------React.memo---------------------------------------
function app() {
	const [count, setCount] = useState(0);
	return (
		<div className="App">
			<h1> Count: {count}</h1>
			<button type="button" onClick{() => {
				setCount(count+1)
			}}> ADD </button>
			<ChildComponent title="This is a title"/>  // Primitive data type will not cause re-renders using React.memo
		</div> 
	)
}
// CHildComponent.jsx
function ChildComponent({title}) {
	<h1>{title}</h1>
	console.log("Child Rerendered!!");
}
export default React.memo(ChildComponent);


---------------------------------------useMemo-----------------------------------------
// Use Memo also handles Composite datatypes like onject and array

function app() {
	const [count, setCount] = useState(0);
	const array = useMemo(() => {
		return ["One", "Two", "Three"];
	},[]);
	
	return (
		<div className="App">
			<h1> Count: {count}</h1>
			<button type="button" onClick{() => {
				setCount(count+1)
			}}> ADD </button>
			<ChildComponent array={array}/>
		</div> 
	)
}
// ChildComponent.jsx
function ChildComponent({array}) {
	<h1>{array}</h1>
	console.log("Child Rerendered!!");
}
export default React.memo(ChildComponent);
 
---------------------------------------useCallback-------------------------------------
// In case of memoising functions

function app() {
	const [count, setCount] = useState(0);
	
	const fetchData = useCallback(type => {
		return fetch(`https://jsonplaceholder.typicode.com/${type}`)
			.then(response => response.json())
			.then(json => console.log(json))
	}, []);
	
	useEffect(() => {
		fetchData("todos";)
	}, []);
	
	return (
		<div className="App">
			<h1> Count: {count}</h1>
			<button type="button" onClick{() => {
				setCount(count+1)
			}}> ADD </button>
			<ChildComponent fetchData={fetchData}/>
		</div> 
	)
}
// ChildComponent.jsx
function ChildComponent = props => {
	console.log("Child Rerendered!!");
	useEffect(() => {
		props.fetchData("users");
	},[]);
	return (
		<h1>ChildComponents</h1>
	)
}
export default React.memo(ChildComponent);


************* Best Option is to stick the properties out of component as normal js to prevent it from re-rendering and escape from using useMemo ********************

==================================React.lazy()=========================================
import React, { lazy, Suspence } from "react";
const MyComp = lazy(() => import("./components/myComp"));

const app = () => {
	return (
		<div className = "App">
			<Suspence fallback={<div>Loading...</div>}>
				<MyComp />
			</Suspence>
		</div>
	)
}


===========================================ref=========================================
=====================================HigherOrderComponent==============================
========================================PureComponent==================================
========================================CustomHooks====================================


--------------------------------------Render Props-------------------------------------


=======================================REDUX===========================================
const dispatch = useDispatch();
const counter = useSelector(state => state.counter);

<h1>Counter: {counter}</h1>
<button onClick={() => dispatch({type: "INCREMENT"})}> INCREMENT</button>




// --------------------Next.js---------------------
npx create-next-app hello-app --use-npm
npm i typescript @types/node @types/react --save-dev
npm run dev


// getInitialProps
const Page = ({custom}) => (
  <div>
    <div>Prop from getInitialProps {this.props.custom}</div>
  </div>
);
Page.getInitialProps = ({pathname, query}) => ({        
  custom: 'custom' // pass some custom props to component
});
export default Page;


// _document.tsx
import Document, { Html, Head, Main, NextScript } from 'next/document';

export default class CustoDocument extends Document {
	render() {
		return (
			<Html>
				<Head>
					<meta property="custom" content="yolo" />
				</Head>
				<body>
					<Main />
				</body>
				<NextScript>
			</Html>
		)
	}
}

// _app.tsx //Root component: will get executed on every page and js will be executed on server as well as browser
import './main.css';  //Global CSS or use 'import styles from main.module.css' for other child components or pages
export default function App({ Component, pageProps }) {
	return (<Component {...pageProps} />);  
} 

// index.tsx   
export default function Home() {
	return(
		<div>
			<h1 className="title">Hello World</h1>
			<style jsx global>
				{`
					.title {
						color: red;
					}
				`}
			</style>
		</div>
	)
}

//NextLink
import NextLink from "next/link";

<NextLink href="/login">login</NextLink>


// fruit/[name]/[subname].tsx
import {useRouter} from 'next/router';
export default function name() {

const router = useRouter();

function takeHome() {
	router.replace('/');
}
return <h1>{router.query.name}/{router.query.subname}</h1>
}

// Catch all routes [...name].tsx
return <h1>{...router.query.name}</h1>
// Optionally cath all cases using [[name]].tsx using base domain


// 404 pages
404.tsx

// SERVERSIDE EXECUTION
// Get Static props and Get Static Paths
// Execution on Server and creates static files after first execution
export const getStaticProps: GetStaticProps = async (context) => {
	return {
		revalidate: 10 // will try to reload after 10s, and only works on prod
		props: {
			myFavNum: 23
		}
	}
}

// Has to implemented with getStaticProps if it is a "Dynamic page"
// GetStaticProps rins on BUILD TIME. It does NOT RUN at RUMTIME

// BUILD START
// localhost:3000/fruit/hello -> take the output -> store it on the disk
// localhost:3000/fruit/world -> take the output -> store it on the disk
export const getStaticPaths: GetStaticPaths = async () => {
	return {
		paths: [{
			{ params: {name: 'hello'} },
			{ params: {name: 'world'} }
		}],
		fallback: false  
		// if true other dynamic paths wont return 404 instead they will call getStaticProps and it will be slower as there wont be that dynamic page on BUILD TIME
		// Wheen true it will return empty props at starting to ignore that we can show loading by following 1.1
		// From next time in prod it wont show loading after first time as it saves the page in sever
	}
}

export default function Dynamic(props) {
	// 1.1 
	// const router = useRouter();
	// if (router.isFallback) {
	//	return <h1>Loading...</h1>
	//}
	
	return <h1>Dynamic num - {props.myFavNum}</h1>
}

// EXECUTION ON SERVERSIDE
// called always on every page request. EVEN ON PRODUCTION
export const getServerSideProps: GetServerSideProps = async (constext) => {
	return {
		props: {
			myFavNum: Math.random()
		}
	}
}


// ====================== Debouncing ======================

<body>
	<input type="text" onKeyUp="betterFunction">
	<script src="./js/index.js"></script>
</body>
// index.js
const getData = () => {
	// call API and gets data
	console.log("Fetching data...");
}

const doSomething = function (fn, d) {
	let timer;
	return function () {
		let context = this, args = arguments;
		clearTimeout(timer);
		timer = setTimeout(() => {
			getData.apply(context, arguments);
		}, d);
	}
}

const betterFunction = doSomething(getData, 300);

// ===================== Throttling =======================



// --------------------RxJS------------------------