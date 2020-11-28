import { Moment } from 'moment';

export interface ITecnicasEvaluativasXItem {
  id?: number;
  idTecnicasEvaluativasXItem?: number;
  tiFechaCreacion?: Moment;
  tiFechaModificacion?: Moment;
  tiNombre?: string;
}

export class TecnicasEvaluativasXItem implements ITecnicasEvaluativasXItem {
  constructor(
    public id?: number,
    public idTecnicasEvaluativasXItem?: number,
    public tiFechaCreacion?: Moment,
    public tiFechaModificacion?: Moment,
    public tiNombre?: string
  ) {}
}
