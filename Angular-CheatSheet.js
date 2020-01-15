//Angular
1. Decorators

	i.Member Decorators
	ii.Class Decorators

2. Lifecycle Hooks

	i.OnChanges
	ii.OnInit
	iii.DoCheck
	iv.AfterContentInit
	v.AfterContentChecked
	vi.AfterViewInit
	vii.AfterViewChecked
	viii.OnDestroy

3. Component 
	(ctrl+`) open terminal in VScode`
	 	-> ng generate component hello

4. Interpolation and Expression

	Eg. {{ myVar }}
	Interpolation Syntax -> {{ }}
	Expression -> myVar

5. Data Binding
   	
   	One Way:
	   	Source to target -> [Property Binding]
	   	Target to source -> (click)="callMyFunction()"
	   						(input)="updateValue($event)"

	Two Way: [(ngModel)]="text"

				import {FormsModule} from './app.component';
				imports:[
					FormsModule
				]

6. *ngFor
		record = [
					{
						name: "hey",
						id: "1"
					},
					{
						name: "hey1",
						id: "11"
					}
				]

		<table>
			<tr *ngFor="let record of records; let i = index; let e = even; let o = odd; let f = first; let l = last" 
			[ngClass]="{odd:o, even:e, first:f, last:l}">
				<td>{{i}}</td>
				<td>{{record.name}}</td>
			</tr>
		</table>


7. Services

	-> ng generate service serviceName
	-> record.service.ts
		@Injectible
		export class RecordService{
			getData(){
				return  [
							{
								name: "hey",
								id: "1"
							},
							{
								name: "hey1",
								id: "11"
							}
						]
			}
		}
	-> app.module.ts
		import {serviceNameService} from './record.service';

		providers: [RecordService]

	-> app.component.ts
		import {serviceNameService} from './record.service';

		export class AppComponent impliments OnInit {
			records = {}
			constructor(private myFirstService : RecordService){

			}
			ngOnInit(){
				this.records = this.myFirstService.getData();
			}
		}

8. HttpClient module

	->app.module.ts
		import { HttpClientModule } from '@angular/common/http';

		imports: [HttpClientModule]

	->records.service.ts
		import { HttpClientModule } from '@angular/common/http';
		import { Injectable } from '@angular/core';

		interface myData{
			obj: Object;
		}

		@Injectible
		export class RecordService{

			constructor(private http: HttpClientModule){}

			getData(){
				return this.http.get<myData>('/api/file.html')
				.subscribe(data => {	
						console.log("We Got", data.obj)
					
				});
			}
		}

9. Proxy Configuration
	
	-> package.json
			"scripts" : {
				"start" : "ng serve --proxy-config proxyconfig.json"
			}

	-> proxyconfig.json
			{
				"/api": {
					"target": "http://localhost:1234",
					"secure": false,
					"changeOrigin": true
				}

			}

10. Routing

		->app.module.ts
				import {RouterModule} from '@angular/router';

				imports: [
							RouterModule.forRoot([
								{
									path:'data',
									component: DataComponent
								},
								{
									path:'',
									component: HomeComponent
								}
							])
						 ]

		->app.component.html
			<p> FIXED TEXT </p>																															  /
			<router-outlet></router-outlet>                                                                                                           	  /

		->home.component.html
			<a routerLink="/data">Go to data page</a>																									  /

		->data.component.html
			<a routerLink="/">Go Back!</a>																												  /

11. Pipes
	- Inbuilt Pipes
		~Date
		~UpperCase
		~LowerCase
		~Currency
			{{ amount | currency }}
			{{ amount | currency: 'INR'}}
		~Percent

	- Custom Pipes

		->ng generate pipe revesrse
		->reverse.pipe.ts
		import { Pipe, PipeTransform } from '@angular/core';

		@Pipe({
			name: 'rerverse'
		})
		export class ReversePipe impliments PipeTransform {
			transform(value: any, args?: any): any {
				let newString = value;
				if(args[0]){
					newString += ".";
				}
				if(args[1] === "singlequote"){
					newString = "'"+newString+"'";
				}
				return newString;
			}
		}

		->app.component.html
			{{"This is a reverse string"|reverse:true:"singlequote"}}

12. Directives
	- 	Inbuilt Directives
			~	ngFor
			~	ngIf
				<div *ngIf="someVar; then trueBlock else falsrBlock">Yes!</div>																			
                <ng-template #trueBlock> <p> yes </p></ng-template>
                <ng-template #falsrBlock> <p> no </p></ng-template>                                                                                       /
                    .ts
					someVar=true;
			~	ngSwitch
			~	ngNonBindable
					<p>using interpolation like <span ngNonBindable>{{interpolation}}</span></p>
			~	ngClass
				    <ul>
				    	<li *ngFor="let info of data">
				    	 <span [ngClass]="{'javascript':info.lang === 'JavaScript','swift':info.lang === 'Swift'}">{{info.lang}}</span>/
				    	 is mostly used on {{info.usedOn}}
				    	</li>
				    </ul>

				->export class AppComponent{
					data = [
							{
								lang: 'JavaScript',
								usedOn: 'web'
							},
							{
								lang: 'Swift',
								usedOn: 'iOs'
							}
					]
				}
			

	- 	Custom Directives
			-> ng generate directive redblack
			-> redblack.directive.ts

				import { Directive } from '@angular/core';
				import { ElementRef } from '@angular/core';

				@Directive({
					selector: '[appRedblack]'
				})
				export class redblackDirective {
					constructor(private el: ElementRef){ 
						el.nativeElement.style.textColor = "white";
						el.nativeElement.style.backgroundColor = "black";
						el.nativeElement.innerText += " - rendered by appRedblack";
					}


				}

			-> app.component.html
				<p>This paragraph is a normal one</p>
				<p appRedblack>This paragraph is a custom directive one</p>

13. Redux Introduction
		
		->npm install @ngrx/store --save                                                                                                                /
		->app.module.ts
			import { StoreModule } from '@ngrx/store';
			import { appReducers } from './reducers';
			imports:[
				StoreModule.forRoot(reducers, {}),
			]

		->appReducers.ts

-----------------------------------------------------------------------------------------------------------------------------------------------------------
X.  
	->app.module.ts
				import {RouterModule} from '@angular/router';
				import { HttpClientModule } from '@angular/common/http';

				@NgModule({
					declerations: [
									AppComponent,
									LoginComponent,
									AdminComponent,
									HomeComponent
								  ],
					imports: [	
								HttpClientModule,
								RouterModule.forRoot([
									{
										path:'login',
										component: LoginComponent
									},
									{
										path:'admin',
										component: AdminComponent
									},
									{
										path:'',
										component: HomeComponent
									}
								])
							]
						})

	-> app.component.html

		<router-outlet></router-outlet>																													  /

	-> home.component.html

		<a routerLink="/login"> Login </a>																												 /
		<a routerLink="/admin"> Admin </a>																												 /
		
	-> login.component.html

		<form (submit)="loginUser($event)">
			<input type="text" placeholder="Username" id="username">
			<input type="password" placeholder="Password" id="password"> 
			<input type="submit" value="Submit">
		</form>																																			  /

	-> login.component.ts

		export class LoginComponent impliments OnInit {
			constructor(private Auth : AuthService){}
			ngOnInit(){

			}

			loginUser(event){
				event.preventDefault();
				const target = event.target;
				const username = target.querySelector('#username').value;
				const password = target.querySelector('#password').value;
				this.Auth.getUserDetails(username, password).
				subscribe(data => {
					if(data.success){

					}else{
						windows.alerst(data.message);
					}
				});

			}
		}


	-> auth.service.ts

	   import { HttpClientModule } from '@angular/common/http';
	   import { Injectable } from '@angular/core';

	   @Injectible
		export class AuthService{

			constructor(private http: HttpClientModule){}

			getUserDetails(){
				return this.http.post('/api/auth.html',{
					username,
					password
				});
			}
		}

11. Route Guards
		-> CanActivate
		-> CanActivateChild
		-> CanDeactivate
		-> Resolve
		-> CanLoad

		-> ng generate guard auth
		-> auth.guard.ts

			import { AuthService } from './auth.service';

			@Injectible
			export class AuthGuard impliments canActivate {
				constructor( private auth : AuthService){}

				canActivate(
					next: ActivateRouteSnapshot,
					state: RouterStateSnapshot): Ovservable<boolean> | promise<boolean> | boolean {
					return this.auth.isLoggedIn;
				}
			}

		-> auth.service.ts

		   import { HttpClientModule } from '@angular/common/http';
		   import { Injectable } from '@angular/core';

		   interface myData {
			   	success: boolean,
			   	message: string,
			   	//token: string
		   }

		   @Injectible
			export class AuthService{

				private loggedInStatus = JSON.parse(localStorage.getItem('loggedIn') || 'false');
				//private tokenId = JSON.parse(localStorage.getItem('tokenId') || 'null');
				constructor(private http: HttpClientModule){}

				setLoggedIn(value: boolean){
					this.loggedInStatus = value;
					localStorage.setItem('loggedIn', 'true');
				}
				/*setTokenId(tokenId: string){
					this.tokenId = tokenId;
					localStorage.setItem('tokenId', tokenId);
				}*/

				get isLoggedIn(){
					return this.loggedInStatus;
				}

				/*get getTokenId(){
					return this.tokenId;
				}*/

				getUserDetails(){
					return this.http.post<myData>('/api/auth.html',{
						username,
						password
					});
				}
			}

		-> login.component.ts
				import {RouterModule} from '@angular/router';

				export class LoginComponent impliments OnInit {
					constructor(private Auth : AuthService, private router: Router){}
					ngOnInit(){

					}

					loginUser(event){
						event.preventDefault();
						const target = event.target;
						const username = target.querySelector('#username').value;
						const password = target.querySelector('#password').value;
						this.Auth.getUserDetails(username, password).
						subscribe(data => {
							if(data.success){
								this.router.navigate(['admin']);
								this.Auth.setLoggedIn(true);
								//this.Auth.setTokenId(data.token);
							}else{
								windows.alert(data.message);
								this.router.navigate(['login']);
							}
						});

					}
				}
		------------------------------------log-out----------------------------------------------
		-> logout.component.html

			<button ng-click="logoutUser($event)">
				
		-> logout.component.ts
				import {RouterModule} from '@angular/router';

				export class LogoutComponent impliments OnInit {
					constructor(private Auth : AuthService, private router: Router){}
					ngOnInit(){

					}

					logoutUser(event){
						event.preventDefault();
						Auth.setLoggedIn('false');
						Auth.setTokenId('null');
						this.router.navigate(['login']);	
					}
				}
		-----------------------------------------------------------------------------------------

		-> app.module.ts

			import { AuthGuard } from './auth.guard';

				@NgModule({
					imports: [	
								HttpClientModule,
								RouterModule.forRoot([
									{
										path:'login',
										component: LoginComponent
									},
									{
										path:'admin',
										component: AdminComponent,
										canActivate: [AuthGuard]
									},
									{
										path:'',
										component: HomeComponent
									}
								])
							],
							providers: [AuthService, AuthGuard]
						})
