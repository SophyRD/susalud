import { Moment } from 'moment';

export interface IFuenteReferencial {
  id?: number;
  idFuenteReferencial?: number;
  frNombre?: string;
  frFechaCreacion?: Moment;
  frFechaModificacion?: Moment;
  idAutoevalucionXproceso?: number;
  idAutoevalucion?: number;
  idMes?: number;
  idUsuariosXevaluacion?: number;
  idEstado?: number;
  idVerificador?: number;
  idFuenteReferenciaXItem?: number;
}

export class FuenteReferencial implements IFuenteReferencial {
  constructor(
    public id?: number,
    public idFuenteReferencial?: number,
    public frNombre?: string,
    public frFechaCreacion?: Moment,
    public frFechaModificacion?: Moment,
    public idAutoevalucionXproceso?: number,
    public idAutoevalucion?: number,
    public idMes?: number,
    public idUsuariosXevaluacion?: number,
    public idEstado?: number,
    public idVerificador?: number,
    public idFuenteReferenciaXItem?: number
  ) {}
}
