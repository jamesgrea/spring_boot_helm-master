# spring_boot_helm

## Instructions

The following guide shows how to get Jenkins deploying this appplication as part of the Dinner and DevOps web series.

### Preparation

**Update your cluster service accounts**

This allows us to run kubectl/helm commands within containers on the cluster.

```
kubectl create clusterrolebinding permissive-binding --clusterrole=cluster-admin --user=admin --user=kubelet --group=system:serviceaccounts
```

**Update Jenkins with your AWS credentials**

Open up Jenkins in your browser. On the main page Jenkins homepage click **Credentials**

In the credentials page, click the **global** to store some credentials locally.

Once loaded clicked **Add Credentials**

In the subsequent form, choose **AWS Credentials** from the **Kind** drop down

For the **ID** enter **aws-access** this maps to the value shown in the [Jenkinsfile](./Jenkinsfile)

You can leave description blank

For the **Access Key ID** and **Secret Access Key** enter the values from your terraform credentials.

You can retrieve these by doing `cat ~/.aws/credentials`

### CI/CD

**Pipeline creation**

Follow through the steps on the videos in Google Classroom to create the course-day-service

**Building from your own GitHub repository**

Once you've got the Tech Returners course-day-service successfully deploying, try forking our repository and getting your own application (in your own GitHub repo) deployed.

You'll need to update the __Jenkinsfile__ with the correct GitHub location, as well as the GitHub reference on the Jenkins job configuration.

### Full removal and destroying

**Fully destroying your cluster**

Now that you have deployed applications such as Jenkins and our spring boot application, the destroy process needs to include their removal.

To destroy things run:

```
helm uninstall course-day-service
helm uninstall jenkins-app
```

Then navigate back into your terraform directory. For example:

```
cd provisioning_eks_terraform/environments/dev 
```

And then perform

```
terraform destroy
```