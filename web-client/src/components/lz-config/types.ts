export const PERIOD_TYPES = ['Dias', 'Meses', 'Anos', 'Semanas'] as const
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

export const VALID_COLUMN_TYPES = ['TEXTO', 'INTEIRO', 'DECIMAL', 'VERDADEIRO / FALSO', 'DATA'] as const
export type ValidColumnType = (typeof VALID_COLUMN_TYPES)[number]

export type ColumnConfig = {
	index: number
	name: string
	type: ValidColumnType
	canBeNull: boolean
	description: string
	status: number
	hash: number
	valid: number
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
		hash: number
		valid: number
	}>
}

export type BConfig = {
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
		hash: number
		valid: number
	}>
}
