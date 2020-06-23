//---------------------History------------------------
	// Is used to redirect from component to a link
	// Also used to override a page from history by replacing
	//Eg.
	this.props.history.push("/movies");
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
=======================================REDUX===========================================
// Used to update store takes current instance of store as an argument and returns the updated store
// Action will describe what action just happened
// Reducer: state and action
const reducer = function(state, action){
	if(action.type === "ATTACK")
		return action.payload;
	if(action.type === "GREENATTACK")
		return action.payload;
	return state;
};
// Store: reducer and state
const store = createStore(reducer, "Peace");
// Step 3: Subscribe to store
const unsubscribe = store.subscribe(() => {
	console.log("Store is now", store.getState());
});
// Step 4: Dispatch action
store.dispatch({type: "ATTACK", payload: "Iron Man"})
store.dispatch({type: "GREENATTACK", payload: "HULK"})
// Action 
{type: "ATTACK", payload: "Iron Man"}

unsubscribe();

// ----------------------COMBINING REDUCERS--------------------
const rootReducer = combineReducers({
	reducer1: resducer1,
	reducer2: reducer2
})
//state.reducer1.

// ---------------------APPLYING MIDDLEWEAR---------------------
const store = createStore(rootReducer, applyMiddlewear(logger))