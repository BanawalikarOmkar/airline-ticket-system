import airportLocationInterface from './airportLocation';

export  interface flightOptionsInteface { 
	id: number
	from: airportLocationInterface
	fromAirportName? : string
	toAirportName? : string
	fromLocation  ? :string
	toLocation ? :string
	flightId ? :number
	to: airportLocationInterface
	airlineName: string
	flightNumber: string
	startTime : Date
	endTime: Date
	totalPrice : number
	baggageinfo : string
	refundableType : RefundType


}

export  interface flightOptionsInteface2 { 
	date : {
		startDate : Date,
		returnDate : Date
	},
	location:{
		fromLocationCtrl : airportLocationInterface
		toLocationCtrl : airportLocationInterface
	},
	ticket : {
		adults : number
	},
	tripCtrl: string


}

export enum RefundType  {
    NON_REFUNDABLE,
    PARTIAL_REFUNDABLE,
    FULLY_REFUNDABLE
}