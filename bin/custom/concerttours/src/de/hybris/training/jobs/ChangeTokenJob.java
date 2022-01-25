package de.hybris.training.jobs;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.training.service.TokenizerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.util.UUID;

public class ChangeTokenJob extends AbstractJobPerformable<CronJobModel>
{
    private static final Logger LOG = Logger.getLogger(ChangeTokenJob.class);
    private TokenizerService tokenizerService;

    @Required
    public TokenizerService getTokenizerService()
    {
        return tokenizerService;
    }

    @Required
    public void setTokenizerService(TokenizerService tokenizerService)
    {
        this.tokenizerService = tokenizerService;
    }

    @Override
    public PerformResult perform(CronJobModel cronJobModel)
    {
        LOG.info("Changing tokens...");
        tokenizerService.getTokenizers().forEach(tokenizer ->
        {
            tokenizer.setToken(UUID.randomUUID().toString());
            modelService.save(tokenizer);
        });
        LOG.info("Done. All tokens changed");

        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }
}
