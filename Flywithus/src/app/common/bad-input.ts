
import {AppError} from './app-error';


export class BadInput extends AppError {
	
	constructor(argument) {
		// code...
		super(argument);
	}
}