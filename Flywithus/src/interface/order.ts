export class Order{
	

	constructor(
		public username: string,
		public orderDate :Date,
		public adultTickets :number,
		public flightId:number,
		public orderId? :number,
		public fromAirport? :string,
		public toAirport? :string){

	}

	
}