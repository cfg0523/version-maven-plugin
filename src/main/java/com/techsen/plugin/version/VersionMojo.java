package com.techsen.plugin.version;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.manager.ScmManager;
import org.apache.maven.scm.plugin.AbstractScmMojo;
import org.apache.maven.scm.provider.ScmProvider;
import org.apache.maven.scm.provider.git.gitexe.GitExeScmProvider;
import org.apache.maven.scm.provider.git.repository.GitScmProviderRepository;
import org.apache.maven.scm.repository.ScmRepository;

@Mojo(name = "list")
public class VersionMojo extends AbstractScmMojo {

    public void execute() throws MojoExecutionException {
        super.execute();

        Log log = getLog();

        try {

            ScmManager manager = getScmManager();

            ScmRepository repository = getScmRepository();

            GitScmProviderRepository providerRepository = (GitScmProviderRepository) repository.getProviderRepository();

            GitExeScmProvider provider = (GitExeScmProvider) manager.getProviderByRepository(repository);
            
            CommandParameters parameters = new CommandParameters();
            
            StatusScmResult result = provider.status(repository, getFileSet());
            //ChangeLogScmResult result = provider.changelog(providerRepository, getFileSet(), parameters);
            
            log.info("***********************\n");
            
            log.info(result.getChangedFiles() + "\n");
            
            log.info("***********************\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
