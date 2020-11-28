import { Moment } from 'moment';

export interface IMacroproceso {
  id?: number;
  idMacroproceso?: number;
  maFechaCreacion?: Moment;
  maFechaModificacion?: Moment;
  verificadorlIdVerificador?: number;
}

export class Macroproceso implements IMacroproceso {
  constructor(
    public id?: number,
    public idMacroproceso?: number,
    public maFechaCreacion?: Moment,
    public maFechaModificacion?: Moment,
    public verificadorlIdVerificador?: number
  ) {}
}
