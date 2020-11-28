import { Moment } from 'moment';

export interface ICriteriosEvaluacionXItem {
  id?: number;
  idCriteriosEvaluacionXItem?: number;
  ceiFechaCreacion?: Moment;
  ceiFechaModificacion?: Moment;
  ceiNombre?: string;
}

export class CriteriosEvaluacionXItem implements ICriteriosEvaluacionXItem {
  constructor(
    public id?: number,
    public idCriteriosEvaluacionXItem?: number,
    public ceiFechaCreacion?: Moment,
    public ceiFechaModificacion?: Moment,
    public ceiNombre?: string
  ) {}
}
