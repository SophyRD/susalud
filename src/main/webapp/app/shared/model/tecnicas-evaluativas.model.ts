import { Moment } from 'moment';

export interface ITecnicasEvaluativas {
  id?: number;
  idTecnicasEvaluativas?: number;
  teNombre?: string;
  teFechaCreacion?: Moment;
  teFechaModificacion?: Moment;
  idAutoevalucionXproceso?: number;
  idAutoevalucion?: number;
  idMes?: number;
  idUsuariosXevaluacion?: number;
  idEstado?: number;
  idVerificador?: number;
  idTecnicasEvaluativasXItem?: number;
}

export class TecnicasEvaluativas implements ITecnicasEvaluativas {
  constructor(
    public id?: number,
    public idTecnicasEvaluativas?: number,
    public teNombre?: string,
    public teFechaCreacion?: Moment,
    public teFechaModificacion?: Moment,
    public idAutoevalucionXproceso?: number,
    public idAutoevalucion?: number,
    public idMes?: number,
    public idUsuariosXevaluacion?: number,
    public idEstado?: number,
    public idVerificador?: number,
    public idTecnicasEvaluativasXItem?: number
  ) {}
}
