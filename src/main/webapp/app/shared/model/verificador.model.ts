import { Moment } from 'moment';

export interface IVerificador {
  id?: number;
  idVerificador?: number;
  v1FechaCreacion?: Moment;
  v1FechaModificacion?: Moment;
}

export class Verificador implements IVerificador {
  constructor(public id?: number, public idVerificador?: number, public v1FechaCreacion?: Moment, public v1FechaModificacion?: Moment) {}
}
