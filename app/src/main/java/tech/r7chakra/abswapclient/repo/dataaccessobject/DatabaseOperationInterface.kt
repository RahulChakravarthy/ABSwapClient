package tech.r7chakra.abswapclient.repo.dataaccessobject

interface DatabaseOperationInterface<T> {

    //Run before database operation starts
    fun beforeOperation()

    //Run after database operation starts
    fun afterOperation(result : T)
}