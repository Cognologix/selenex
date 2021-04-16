package com.cadent.test.utils.google;


import com.cadent.test.annotation.PageFragment;
import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.bigquery.*;
import com.google.cloud.storage.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *  GCP utils should be used to stablish the connection with GCP .
 *
 *  The mandatory file is required Service Json of GCp.
 * */
@PageFragment
public class GCPUtils {

    /** The Constant logger. */
    private static final Logger LOGGER     = LoggerFactory.getLogger(GCPUtils.class.getName());

    private final static List<String> WRITER_ACL = Arrays.asList("storage.objects.create",
            "storage.objects.delete", "storage.objects.list");

    private final static List<String> READER_ACL = Collections.singletonList("storage.objects.list");

//    public static Resource resource;
//
//    public GCPUtils(Resource resource){
//
//        this.resource = resource;
//    }

    /**
     *  GCP json file , needs to be included in the child project.
     * */
    @Value ("${classpath:gcp_qa_service_config.json}")
    private Resource resource;

    /**
     *  Preject ID is must to establish an connection
     * */
    @Value ("${project.id}")
    private String projectId;

    /**
     *  Establish the connection with GCP and return GoogleCredentials Object.
     * */
    public  GoogleCredentials initGoogleCloudConfiguration(){

        GoogleCredentials credentials=null;

        try (FileInputStream serviceAccountStream = new FileInputStream (resource.getFile ())) {

            credentials = ServiceAccountCredentials.fromStream(serviceAccountStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        }

        return credentials;
    }


    /**
     * This method can be accessed , to make an query to BigQuery
     * */

    public BigQuery getBigQuery(GoogleCredentials credentials){

        BigQuery bigquery =null;
        try {
            bigquery =  BigQueryOptions.newBuilder ().setCredentials (credentials).setProjectId (projectId).build ()
                    .getService ();
        }catch (Exception exception){

            LOGGER.info ("Connection has not been established with bigQuery "+ exception.getMessage ());
        }

        return bigquery;
    }

    /**
     * Test connection to gs bucket boolean.
     *
     * @param storage the storage
     * @param bucketName the bucket name
     * @param checkWritePermission the check write permission
     * @return the boolean
     */
    public static Boolean testConnectionToGSBucket(final Storage storage, final String bucketName,
                                                   final Boolean checkWritePermission) {
        try {

            final List<String> permissions = checkWritePermission ? WRITER_ACL : READER_ACL;

            final List<Boolean> acls = storage.testIamPermissions(bucketName, permissions);
            return acls.stream().allMatch(val -> val);

        } catch (final Exception e) {
            LOGGER.error("Exception in connectToGSBucket ", e);
            return false;
        }

    }

    /**
     * Gets storage.
     *
     * @return the storage
     */
    public Storage getStorage() {
        final GoogleCredentials configuration = initGoogleCloudConfiguration();
        return StorageOptions.newBuilder().setCredentials(configuration).build().getService();
    }

    /**
     * List page.
     *
     * @param bucketName the bucket name
     * @param directoryName the directory name
     * @return the page
     */
    public Page<Blob> list(final String bucketName, final String directoryName) {
        return list(getStorage(), bucketName, directoryName);
    }

    /**
     * Get blob.
     *
     * @param bucketName the bucket name
     * @param objectName the object name
     * @return the blob
     */
    public Blob get(final String bucketName, final String objectName) {
        return getStorage().get(bucketName, objectName);
    }

    public void downloadObject(final String bucketName,final String objectName,final Path path){

         get(bucketName,objectName).downloadTo (path);
    }

    public Page<Blob> list(final Storage storage, final String bucketName, final String directoryName) {

        return storage.list(bucketName, Storage.BlobListOption.prefix(directoryName));
    }

    /**
     * Test connection to gs bucket boolean.
     *
     * @param bucketName the bucket name
     * @param checkWritePermission the check write permission
     * @return the boolean
     */
    public Boolean testConnectionToGSBucket(final String bucketName, final Boolean checkWritePermission) {
        return testConnectionToGSBucket(getStorage(), bucketName, checkWritePermission);
    }



//    public static void downloadObject() throws Exception, IOException {
//
//        Path destFilePath = Paths.get("/home/avalvekar/testgit/DID_CommaPipe_7z.7z");
//
//        Storage storage = StorageOptions.newBuilder()
//                .setCredentials(ServiceAccountCredentials.fromStream(
//                        new FileInputStream("/home/avalvekar/project/vaishali/test-AS/git4info/test-common/auth.json")))
//                .setProjectId("audience-solutions-qa").build().getService();
//
//        Bucket bucket = storage.get("asq-as-raw-data-sets",
//                Storage.BucketGetOption.fields(Storage.BucketField.values()));
//
//        if (bucket.getLabels() != null) {
//            System.out.println("\n\n\nLabels:");
//            for (Map.Entry<String, String> label : bucket.getLabels().entrySet()) {
//                System.out.println(label.getKey() + "=" + label.getValue());
//            }
//        }
//        Blob blob = storage.get(BlobId.of("qa_dataset_files", "did/DID_CommaPipe_7z.7z"));
//        blob.downloadTo(destFilePath);
////
//        System.out.println("Downloaded object " + "did/DID_CommaPipe_7z.7z" + " from bucket name " + "qa_dataset_files"
//                + " to " + destFilePath);
//    }

//    public static GoogleCredentials initGoogleCloudConfiguration(final String gConfig) {
//        final GoogleCredentials googleConfiguration;
//        try {
//            logger.info("Getting google configuration from Generic Spec ");
//            final String config = gConfig.replace("\n", "\\n");
//            final ServiceAccountConfiguration dataSetsApiGcpConfig = (ServiceAccountConfiguration) JsonUtil
//                    .toObject(config, ServiceAccountConfiguration.class);
//            googleConfiguration = ServiceAccountCredentials.fromStream(
//                    IOUtils.toInputStream(JsonUtil.toString(dataSetsApiGcpConfig), Charset.defaultCharset()));
//
//        } catch (final IOException e) {
//            logger.error("Exception while in google configuration saved in  database config ", e);
//            logger.debug("\n Google config is {}", gConfig);
//            throw new AutomationRunTimeError (e);
//        } catch (final Exception e) {
//            logger.error("Exception while getting google configuration from database config ", e);
//            throw new AutomationRunTimeError(e);
//        }
//        return googleConfiguration;
//    }
}
