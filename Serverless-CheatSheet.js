----------------Serverless----------------
// Webinar - Getting started with the serverless framework
// https://www.youtube.com/watch?v=LXB2Nv9ygQc

$ cd projectName
$ serverless create --template aws-nodejs / aws-nodejs-typescript
$ npm init
$ npm install aws-sdk --save
$ npm install --save-dev serverless-pseudo-parameters
-----------serverless.yml-------------
serveice: projectName

provider:
	name: aws
	runtime: nodejs10.x

	iamRoleStatements:
		- Effect: "Allow"
		  Action: 
			- dynamodb:PutItem
			- dynamodb:Scan*
			- dynamodb:GetItem
			- dynamodb:UpdateItem
			- dynamodb:DeleteItem
		  Resources: arn:aws:dynamodb:#{AWS::Region}:#{AWS::AccountId}:table/${self:service}-kittens-${opt-stage}
	environment: 
		DYNAMODB_KITTEN_TABLE: ${self:service}-kittens-${opt:stage}
	
	plugins:
		- serverless-pseudo-parameters
	functions:
		create:
			handler: handler.create
			events:
				- http:
					path: /v1/kitten
					method: post
		list:
			handler: handler.list
			events:
				- http:
					path: /v1/kitten
					method: get
		get:
			handler: handler.get
			events:
				- http:
					path: /v1/kitten/{name}
					method: get
		update:
			handler: handler.update
			events:
				- http:
					path: /v1/kitten/{name}
					method: put
		delete:
			handler: handler.delete
			events:
				- http:
					path: /v1/kitten/{name}
					method: delete
					
	resources:
		Resources:
			kittenTable:
				Type: 'AWS::DynamoDB::Table'
				Properties:
					TableName: ${self:service}-kittens-${opt:stage}
					AttributeDefinations:
						- AttributeName: name
						  AttributeType: S
					KeySchema:
						- AttributeName: name
						  KeyType: HASH
					BillingMode: PAY_PER_REQUEST

----------handler.js---------------
'use strict'
const AWS = require('aws-sdk');

module.exports = {
	create: async (event, context) => {
		let bodyObj = {};
		try {
			bodyObj = JSON.parse(event.body)
		} catch (jsonError) {
			console.log('That was a error', jsonError);
			return {
				statusCode: 400
			}
		}
		
		if (typeof bodyObj.name === 'undefined' || typeof body.age === 'undefined') {
			console.log('Missing parameters');
			return {
				statusCode: 400
			}
		}
		
		let putParams = {
			TableName: process.env.DYNAMODB_KITTEN_TABLE,
			Item: {
				name: bodyObj.name,
				age: bodyObj.age
			}
		};
		let putResult = {};
		try {
			let dynamodb = new AWS.DynamoDB.DocumentClient();
			putResult = await dynamodb.put(putParams).promise()
		} catch (putError) {
			console.log('There was a problem putting the kittens');
			console.log('putParams', putParams);
			return  {
				statusCode: 500
			}
		}
		
		return {
			statusCode: 201
		}
	},
	list: async (event, context) => {
	
	},
	get: async (event, context) => {
	
	},
	update: async (event, context) => {
	
	},
	delete: async (event, context) => {
	
	}
	
}

$ serverless deploy --aws-profile serverless --stage dev 
$ serverless logs -f get --stage dev --aws-profile serverless
$ serverless remove --aws-profile serverless --stage dev 


