import { Moment } from 'moment';

export interface IMaestraverificador {
  id?: number;
  idMaestraverificador?: number;
  mvFecha?: Moment;
  mvFechaModificacion?: Moment;
  idVerificador?: number;
}

export class Maestraverificador implements IMaestraverificador {
  constructor(
    public id?: number,
    public idMaestraverificador?: number,
    public mvFecha?: Moment,
    public mvFechaModificacion?: Moment,
    public idVerificador?: number
  ) {}
}
