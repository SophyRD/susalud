import { Moment } from 'moment';

export interface ICriteriosEvaluacion {
  id?: number;
  idCriteriosEvaluacion?: number;
  ceNombre?: string;
  ceFechaCreacion?: Moment;
  ceFechaModificacion?: Moment;
  idAutoevalucionXproceso?: number;
  idAutoevalucion?: number;
  idMes?: number;
  idUsuariosXevaluacion?: number;
  idEstado?: number;
  idVerificador?: number;
  idCriteriosEvaluacionXItem?: number;
}

export class CriteriosEvaluacion implements ICriteriosEvaluacion {
  constructor(
    public id?: number,
    public idCriteriosEvaluacion?: number,
    public ceNombre?: string,
    public ceFechaCreacion?: Moment,
    public ceFechaModificacion?: Moment,
    public idAutoevalucionXproceso?: number,
    public idAutoevalucion?: number,
    public idMes?: number,
    public idUsuariosXevaluacion?: number,
    public idEstado?: number,
    public idVerificador?: number,
    public idCriteriosEvaluacionXItem?: number
  ) {}
}
