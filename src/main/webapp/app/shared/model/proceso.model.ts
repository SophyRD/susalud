import { Moment } from 'moment';

export interface IProceso {
  id?: number;
  idProceso?: number;
  prDescripcion?: string;
  idMacroproceso?: Moment;
  idVerificador?: number;
}

export class Proceso implements IProceso {
  constructor(
    public id?: number,
    public idProceso?: number,
    public prDescripcion?: string,
    public idMacroproceso?: Moment,
    public idVerificador?: number
  ) {}
}
