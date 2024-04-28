export const PERIOD_TYPES = ['Dias', 'Meses', 'Anos'] as const
type PeriodType = (typeof PERIOD_TYPES)[number]

export type MetadataUploadConfig = {
	fileExtension: 'csv'
	hasHeader: boolean
	fileName: string
	separator: string
}

export type MetadataConfig = {
	name: string
	fileOrigin: string
	frequencyNumber: number
	frequencyType: PeriodType
} & MetadataUploadConfig

export const VALID_COLUMN_TYPES = ['TEXTO', 'INTEIRO', 'DECIMAL', 'VERDADEIRO / FALSO'] as const
export type ValidColumnType = (typeof VALID_COLUMN_TYPES)[number]

export type ColumnConfig = {
	index: number
	name: string
	type: ValidColumnType
	canBeNull: boolean
	description: string
	status: number
}

export type LZConfig = {
	fileId: number
	fileName: string
	fileOrigin: string
	filePeriod: string
	fileType: string
	frequency: number
	name: string
	columns: Array<{
		columnId: Number
		columnName: string
		description: string
		empty: number
		status: number
		type: string
	}>
}
