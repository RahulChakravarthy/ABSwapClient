package tech.r7chakra.abswapclient.managers

import android.content.Context
import com.amazonaws.auth.CognitoCachingCredentialsProvider
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3Client
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class S3Manager @Inject constructor(val context : Context) {

    private val S3_BUCKET_NAME = "tabla-s3-bucket"

    val s3Client : AmazonS3Client
    val transferUtility : TransferUtility

    init {
        val credentialProvides = CognitoCachingCredentialsProvider(
            context,
            "us-east-2_rBUWw4pO9",
            Regions.US_EAST_2)
        s3Client = AmazonS3Client(credentialProvides)
        transferUtility = TransferUtility.builder().s3Client(s3Client).context(context).build()
    }
}